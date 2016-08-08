package databuilder.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by richard on 08/08/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BugUpdateModel
{
    @JsonProperty
    private String summary;
    @JsonProperty
    private int[] ids;

    public String getSummary()
    {
        return summary;
    }

    public int[] getIds()
    {
        return ids;
    }

    public BugUpdateModel(String summary, int[] ids)
    {
        this.summary = summary;
        this.ids = ids;
    }
}
