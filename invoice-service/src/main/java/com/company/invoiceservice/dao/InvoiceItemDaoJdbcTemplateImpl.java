package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INVOICE_ITEM_SQL =
            "insert into invoice_item (invoice_id, inventory_id, quantity, unit_price) values (?, ?, ?, ?)";

    private static final String SELECT_INVOICE_ITEM_SQL =
            "select * from invoice_item where invoice_item_id = ?";

    private static final String SELECT_ALL_INVOICE_ITEMS_SQL =
            "select * from invoice_item";

    private static final String UPDATE_INVOICE_ITEM_SQL =
            "update invoice_item set invoice_id = ?, inventory_id = ?, quantity = ?, unit_price = ? where invoice_item_id = ?";

    private static final String DELETE_INVOICE_ITEM =
            "delete from invoice_item where invoice_item_id = ?";

    private static final String SELECT_INVOICE_ITEM_BY_INVOICE_ID =
            "select * from invoice_item where invoice_id = ?";

    private static final String SELECT_TOTAL_PRICE_BY_INVOICE_ID =
            "select sum(unit_price) from invoice_item where invoice_id = ?";

    private static final String SELECT_TOTAL_QUANTITY_BY_INVOICE_ID =
            "select sum(quantity) from invoice_item where invoice_id = ?";

    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(
                INSERT_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        invoiceItem.setId(id);

        return invoiceItem;
    }

    public BigDecimal totalPriceItems(int invoiceId) {
        BigDecimal total = jdbcTemplate.queryForObject(SELECT_TOTAL_PRICE_BY_INVOICE_ID, BigDecimal.class, invoiceId);

        return total;
    }

    public int totalQuantityByInvoice(int invoiceId) {
        int total = jdbcTemplate.queryForObject(SELECT_TOTAL_QUANTITY_BY_INVOICE_ID, Integer.class, invoiceId);
        return total;
    }

    @Override
    public InvoiceItem getInvoiceItemById(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this
            return null;
        }
    }

    @Override
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(int id) {
        return jdbcTemplate.query(SELECT_INVOICE_ITEM_BY_INVOICE_ID, this::mapRowToInvoiceItem,id);
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS_SQL, this::mapRowToInvoiceItem);    }


    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(
                UPDATE_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice(),
                invoiceItem.getId());

    }

    @Override
    public void deleteInvoiceItem(int id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM, id);
    }


    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setInventoryId(rs.getInt("inventory_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnitPrice(rs.getBigDecimal("unit_price"));

        return invoiceItem;
    }
}
