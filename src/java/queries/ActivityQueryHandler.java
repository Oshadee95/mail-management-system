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
public class ActivityQueryHandler implements QueryHandler {

    private final UserQueryHandler userQueryHandler = new UserQueryHandler();
    private final String TABLE_NAME = "activities";
    private final String INSERT_DATA_QUERY = "INSERT INTO `" + TABLE_NAME + "`(`userId`, `action`) VALUES (?, ?)";
    private final String DELETE_DATA_QUERY = "DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?";
    private final String FETCH_DATA_QUERY = "SELECT a.id, a.userId, u.displayName, a.action, a.occuredAt " + "FROM " + TABLE_NAME + " a, " + userQueryHandler.getTableName() + " u WHERE a.userId = u.id  AND a.userId = ?";
    private final String FETCH_ALL_DATA_QUERY = "SELECT a.id, a.userId, u.displayName, a.action, a.occuredAt " + "FROM " + TABLE_NAME + " a, " + userQueryHandler.getTableName() + " u WHERE a.userId = u.id";

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
}
