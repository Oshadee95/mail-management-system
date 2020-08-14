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
public class UserQueryHandler implements QueryHandlerInterface {

    private final String TABLE_NAME = "users";
    private final String VIEW_NAME = "userinfoview";
    private final String INSERT_DATA_QUERY = "INSERT INTO `" + TABLE_NAME + "`(`id`, `nic`, `fullName`, `displayName`, `occupationId`, `office`, `roleId`, `active`, `password`, `photoURL`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_DATA_QUERY = "UPDATE `" + TABLE_NAME + "` SET `fullName`= ?,`displayName`= ?,`occupationId` = ?, `office` = ?, `roleId` = ?, `active` = ?, `password`= ? WHERE id = ?";
    private final String DELETE_DATA_QUERY = "DELETE FROM `" + TABLE_NAME + "` WHERE `id`= ?";
    private final String FETCH_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "` WHERE `uid` = ?";
    private final String FETCH_ALL_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "`";
    private final String FETCH_ALL_LOW_LEVEL_DATA_QUERY = "SELECT * FROM `" + VIEW_NAME + "` WHERE `roleWeight` < 80";

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
    
    public String getFetchAllLowLevelDataQuery(){
        return FETCH_ALL_LOW_LEVEL_DATA_QUERY;
    }
}
