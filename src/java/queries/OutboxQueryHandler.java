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
public class OutboxQueryHandler implements QueryHandlerInterface {

    private final String TABLE_NAME = "outbox";
    private final String VIEW_NAME = "outboxinfoview";
    private final String INSERT_DATA_QUERY = "INSERT INTO `" + TABLE_NAME + "`(`mailId`, `senderId`, `replyImageURL`) VALUES (?, ?, ?)";
    private final String UPDATE_DATA_QUERY = "UPDATE `" + TABLE_NAME + "` SET `senderId`= ? WHERE `mailId` = ?";
    private final String DELETE_DATA_QUERY = "DELETE FROM `" + TABLE_NAME + "` WHERE `mailId`= ?";
    private final String FETCH_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "` WHERE mailId = ?";
    private final String FETCH_ALL_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "` ORDER BY `" + VIEW_NAME + "`.`repliedAt` DESC";
    private final String FETCH_ALL_BY_OFFICE = "SELECT * FROM `" + VIEW_NAME + "` WHERE `office`= ? ORDER BY `" + VIEW_NAME + "`.`repliedAt` DESC";

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

    public String getFetchAllDataByOffice() {
        return FETCH_ALL_BY_OFFICE;
    }
}
