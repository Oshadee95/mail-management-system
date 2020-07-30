package queries;

public class CategoryQueryHandler extends QueryHandler {

    private static final String INSERT_DATA_QUERY = "INSERT INTO `categories`(`id`, `name`, `description`) VALUES (?, ?, ?)";
    private static final String UPDATE_DATA_QUERY = "";
    private static final String DELETE_DATA_QUERY = "DELETE FROM `categories` WHERE `id`=?";
    private static final String FETCH_DATA_QUERY = "";
    private static final String FETCH_ALL_DATA_QUERY = "";
    
    public static String getAddDataQuery() {
        return INSERT_DATA_QUERY;
    }

    public static String getUpdateDataQuery() {
        return UPDATE_DATA_QUERY;
    }

    public static String getRemoveDataQuery() {
        return DELETE_DATA_QUERY;
    }
    
    public static String getFetchDataQuery() {
        return FETCH_DATA_QUERY;
    }

    public static String getFetchAllDataQuery() {
        return FETCH_ALL_DATA_QUERY;
    }
}
