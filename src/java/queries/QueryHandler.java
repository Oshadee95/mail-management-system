package queries;

public interface QueryHandler {

    public String getAddDataQuery();

    public String getUpdateDataQuery();

    public String getRemoveDataQuery();

    public String getFetchDataQuery();

    public String getFetchAllDataQuery();
}
