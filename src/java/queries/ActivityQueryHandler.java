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
public class ActivityQueryHandler implements QueryHandlerInterface {

    private final QueryHandlerInterface userQueryHandler = new UserQueryHandler();
    private final String TABLE_NAME = "activities";
    private final String VIEW_NAME = "activityinfoview";
    private final String INSERT_DATA_QUERY = "INSERT INTO `" + TABLE_NAME + "`(`userId`, `action`) VALUES (?, ?)";
    private final String DELETE_DATA_QUERY = "DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?";
    private final String FETCH_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "` WHERE userId = ?";
    private final String FETCH_ALL_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "`";

    @Override
    public String getAddDataQuery() {
        return INSERT_DATA_QUERY;
    }

    @Override
    public String getUpdateDataQuery() {
        return null;
    }

    @Override
    public String getRemoveDataQuery() {
        return DELETE_DATA_QUERY;
    }

    @Override
    public String getFetchDataQuery() {
        return FETCH_DATA_QUERY; // Fetch all activities filtered by user
    }

    @Override
    public String getFetchAllDataQuery() {
        return FETCH_ALL_DATA_QUERY;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
