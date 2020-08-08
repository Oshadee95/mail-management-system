/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

/**
 *
 * @author RED-HAWK
 */
public class InboxQueryHandler implements QueryHandler {

    private static final String INSERT_DATA_QUERY = "INSERT INTO `inbox`(`id`, `categoryId`, `sender`, `content`, `collectorId`, `recipientId`, `imageURL`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_DATA_QUERY = "";
    private static final String DELETE_DATA_QUERY = "DELETE FROM `inbox` WHERE `id`= ?";
    private static final String FETCH_DATA_QUERY = "";
    private static final String FETCH_ALL_DATA_QUERY = "";
    
    @Override
    public String getAddDataQuery() {
        return INSERT_DATA_QUERY;
    }

    @Override
    public String getUpdateDataQuery() {
        return UPDATE_DATA_QUERY;
    }

    @Override
    public String getRemoveDataQuery() {
        return DELETE_DATA_QUERY;
    }
    
    @Override
    public String getFetchDataQuery() {
        return FETCH_DATA_QUERY;
    }

    @Override
    public String getFetchAllDataQuery() {
        return FETCH_ALL_DATA_QUERY;
    }
}
