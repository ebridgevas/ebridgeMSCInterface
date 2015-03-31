package com.ebridgevas.ss7interface.dao;


import com.ebridge.commons.DataBaseConnectionPool;
import com.ebridgevas.ss7interface.model.SS7Config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.*;

/**
 * david@tekeshe.com
 *
 */
public class SS7ConfigDAO {


    private static Connection connection;

    static {
        try {
            connection = DataBaseConnectionPool.getConnection();
        } catch (Exception e) {
            // TODO handle exception
            e.printStackTrace();
        }
    }

//    public static void persist(TxnDto txn) {
//
//        if ( connection == null) {
//            try {
//                connection = DataBaseConnectionPool.getConnection();
//            } catch (Exception e) {
//                // TODO handle exception
//                e.printStackTrace();
//            }
//        }
//
//        String sql = " INSERT INTO txns (uuid, source_id, destination_id, delivery_channel, transaction_date," +
//                "                   transaction_type, product_code, amount, status_code, narrative," +
//                "                   short_message ) " +
//                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
//
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement(sql);
//            stmt.setLong(  1, txn.getUuid().longValue());
//            stmt.setString(2, txn.getSourceId());
//            stmt.setString(3, txn.getDestinationId());
//            stmt.setString(4, txn.getDeliveryChannel());
//            stmt.setTimestamp( 5, new Timestamp(txn.getTransactionDate().getTime()));
//            stmt.setString(6, txn.getTransactionType());
//            stmt.setString(7, txn.getProductCode());
//            stmt.setBigDecimal(8, txn.getAmount());
//            stmt.setString(9, txn.getStatusCode());
//            stmt.setString(10,txn.getNarrative());
//            stmt.setString(11, txn.getShortMessage());
//
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } finally {
//            try {stmt.close();} catch (Exception e){}
//        }
//
//    }


    public SS7Config findById(String globalTitle) {

        /*
         global_title           | character varying(20) | not null
 destination_point_code | integer               | not null
 ip_address             | character varying(60) | not null
 port                   | integer               | not null
 server_name            | character varying(60) | not null
 association_name       | character varying(60) | not null
 functionality          | character varying(20) | not null
 ip_channel_type        | character varying(20) | not null
 network_appearance     | integer               | not null
 network_indicator      | integer               | not null
 routing_context        | integer               | not null
 service_indicator      | integer               | not null
 sub_system_number      | integer               | not null
         */
        if ( connection == null) {
            try {
                connection = DataBaseConnectionPool.getConnection();
            } catch (Exception e) {
                // TODO handle exception
                e.printStackTrace();
            }
        }

        String sql = " " +
                " SELECT global_title, " +
                "        destination_point_code, " +
                "        ip_address, " +
                "        port, " +
                "        server_name, " +
                "        association_name, " +
                "        functionality, " +
                "        ip_channel_type, " +
                "        network_appearance, " +
                "        network_indicator, " +
                "        routing_context, " +
                "        service_indicator, " +
                "        sub_system_number " +
                " FROM   ss7_config " +
                " WHERE  global_title = '" + globalTitle + "'";


        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery( sql );
            if (rs.next()) {

                try {
                    return new SS7Config(
                            rs.getString("global_title"),
                            rs.getInt("destination_point_code"),
                            rs.getString("server_name"),
                            rs.getString("association_name"),
                            rs.getString("ip_address"),
                            rs.getInt("port"),
                            rs.getInt("routing_context"),
                            rs.getInt("network_indicator"),
                            rs.getInt("service_indicator"),
                            rs.getInt("sub_system_number"),
                            rs.getString("ip_channel_type"),
                            rs.getString("functionality"),
                            rs.getLong("network_appearance"));
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {stmt.close();} catch (Exception e){}
            try {rs.close();} catch (Exception e){}
        }
    }

//    public void persist(SS7Config ss7Config ) {
//        try {
//            EntityManagerFactory entityManagerFactory
//                    =  Persistence.createEntityManagerFactory("ebridge.persistence.jpa");
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//            entityManager.persist(ss7Config);
//            entityManager.flush();
//            entityManager.getTransaction().commit();
//            entityManager.close();
//            entityManagerFactory.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//    public SS7Config findById(String globalTitle) throws Exception {
//
//        EntityManagerFactory entityManagerFactory = null;
//        EntityManager entityManager = null;
//        try {
//             entityManagerFactory = Persistence.createEntityManagerFactory("ebridge.persistence.jpa");
//             entityManager = entityManagerFactory.createEntityManager();
//
//            return entityManager.find(SS7Config.class, globalTitle);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(e );
//        } finally {
//            try { entityManager.close(); } catch (Exception e){}
//            try { entityManagerFactory.close(); } catch (Exception e){}
//        }
//    }
}
