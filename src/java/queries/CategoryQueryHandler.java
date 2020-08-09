package queries;

public class CategoryQueryHandler implements QueryHandler {

    private final String TABLE_NAME = "categories";
    private final String INSERT_DATA_QUERY = "INSERT INTO `"+TABLE_NAME+"`(`name`, `description`) VALUES (?, ?)";
    private final String UPDATE_DATA_QUERY = "";
    private final String DELETE_DATA_QUERY = "DELETE FROM `"+TABLE_NAME+"` WHERE `id`= ?";
    private final String FETCH_DATA_QUERY = "";
    private final String FETCH_ALL_DATA_QUERY = "";
    
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
}
