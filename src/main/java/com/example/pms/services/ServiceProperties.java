package com.example.pms.services;

import com.example.pms.DBConnection;
import com.example.pms.entities.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceProperties {
    private Connection cnx;

    public ServiceProperties() {
        cnx = DBConnection.getInstance().getCnx();
    }

    public List<Properties> getAllProperties() {
        List<Properties> propertiesList = new ArrayList<>();
        try {
            String query = "SELECT * FROM properties";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Properties property = new Properties();
                property.setId(rs.getInt("id"));
                property.setName(rs.getString("name"));
                property.setLocation(rs.getString("location"));
                property.setDescription(rs.getString("description"));
                property.setPricePerMonth(rs.getDouble("price_per_month"));
                property.setAvailableFrom(rs.getDate("available_from"));
                property.setAvailableUntil(rs.getDate("available_until"));
                property.setTenantName(rs.getString("tenant_name"));
                property.setTenantNumber(rs.getString("tenant_number"));
                property.setUserId(rs.getInt("user_id"));
                propertiesList.add(property);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propertiesList;
    }

    public Properties getPropertyById(int propertyId) {
        try {
            String query = "SELECT * FROM properties WHERE id = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, propertyId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Properties property = new Properties();
                property.setId(rs.getInt("id"));
                property.setName(rs.getString("name"));
                property.setLocation(rs.getString("location"));
                property.setDescription(rs.getString("description"));
                property.setPricePerMonth(rs.getDouble("price_per_month"));
                property.setAvailableFrom(rs.getDate("available_from"));
                property.setAvailableUntil(rs.getDate("available_until"));
                property.setTenantName(rs.getString("tenant_name"));
                property.setTenantNumber(rs.getString("tenant_number"));
                property.setUserId(rs.getInt("user_id"));
                return property;
            } else {
                return null; // Property with given ID not found
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProperty(Properties property) {
        try {
            String query = "INSERT INTO properties (name, location, description, pricePerMonth, availableFrom, availableUntil, tenantName, tenantNumber, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, property.getName());
            preparedStatement.setString(2, property.getLocation());
            preparedStatement.setString(3, property.getDescription());
            preparedStatement.setDouble(4, property.getPricePerMonth());
            preparedStatement.setDate(5, new java.sql.Date(property.getAvailableFrom().getTime()));
            preparedStatement.setDate(6, property.getAvailableUntil() != null ? new java.sql.Date(property.getAvailableUntil().getTime()) : null);
            preparedStatement.setString(7, property.getTenantName());
            preparedStatement.setString(8, property.getTenantNumber());
            preparedStatement.setInt(9, property.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProperty(Properties property) {
        try {
            String query = "UPDATE properties SET name = ?, location = ?, description = ?, pricePerMonth = ?, availableFrom = ?, availableUntil = ?, tenantName = ?, tenantNumber = ? WHERE id = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, property.getName());
            preparedStatement.setString(2, property.getLocation());
            preparedStatement.setString(3, property.getDescription());
            preparedStatement.setDouble(4, property.getPricePerMonth());
            preparedStatement.setDate(5, new java.sql.Date(property.getAvailableFrom().getTime()));
            preparedStatement.setDate(6, property.getAvailableUntil() != null ? new java.sql.Date(property.getAvailableUntil().getTime()) : null);
            preparedStatement.setString(7, property.getTenantName());
            preparedStatement.setString(8, property.getTenantNumber());
            preparedStatement.setInt(9, property.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProperty(int propertyId) {
        try {
            String query = "DELETE FROM properties WHERE id = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, propertyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Properties> getPropertiesByUserId(int userId) {
        List<Properties> propertiesList = new ArrayList<>();
        try {
            String query = "SELECT * FROM properties WHERE user_id = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Properties property = new Properties();
                property.setId(rs.getInt("id"));
                property.setName(rs.getString("name"));
                property.setLocation(rs.getString("location"));
                property.setDescription(rs.getString("description"));
                property.setPricePerMonth(rs.getDouble("pricePerMonth"));
                property.setAvailableFrom(rs.getDate("availableFrom"));
                property.setAvailableUntil(rs.getDate("availableUntil"));
                property.setTenantName(rs.getString("tenantName"));
                property.setTenantNumber(rs.getString("tenantNumber"));
                property.setUserId(rs.getInt("user_id"));
                propertiesList.add(property);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propertiesList;
    }

}

