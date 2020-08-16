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
public class InboxQueryHandler implements QueryHandlerInterface {

    private final String TABLE_NAME = "inbox";
    private final String VIEW_NAME = "inboxinfoview";
    private final String INSERT_DATA_QUERY = "INSERT INTO `" + TABLE_NAME + "`(`id`, `type`, `categoryId`, `sender`, `content`, `collectorId`, `recipientId`, `imageURL`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_DATA_QUERY = "UPDATE `" + TABLE_NAME + "` SET `type` = ?, `categoryId`= ?,`sender`= ? ,`content`= ?,`recipientId`= ? WHERE `id` = ?";
    private final String UPDATE_STATUS_QUERY = "UPDATE `" + TABLE_NAME + "` SET `replied`= ? WHERE `id` = ?";
    private final String DELETE_DATA_QUERY = "DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?";
    private final String FETCH_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "` WHERE `id` = ?";
    private final String FETCH_ALL_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "` ORDER BY `" + VIEW_NAME + "`.`recordedAt` DESC";
    private final String FETCH_ALL_BY_USER = "SELECT * FROM `" + VIEW_NAME + "` WHERE `recipientId`= ? ORDER BY `" + VIEW_NAME + "`.`recordedAt` DESC";
    private final String FETCH_ALL_BY_OFFICE = "SELECT * FROM `" + VIEW_NAME + "` WHERE `office`= ? ORDER BY `" + VIEW_NAME + "`.`recordedAt` DESC";

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

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
    
    public String getUpdateStatusQuery(){
        return UPDATE_STATUS_QUERY;
    }
    
    public String getFetchAllDataByUser(){
        return FETCH_ALL_BY_USER;
    }
    
    public String getFetchAllDataByOffice(){
        return FETCH_ALL_BY_OFFICE;
    }
}
