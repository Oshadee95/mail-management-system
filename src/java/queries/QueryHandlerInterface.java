package queries;

public interface QueryHandlerInterface {

    public String getAddDataQuery();

    public String getUpdateDataQuery();

    public String getRemoveDataQuery();

    public String getFetchDataQuery();

    public String getFetchAllDataQuery();
    
    public String getTableName();
}
