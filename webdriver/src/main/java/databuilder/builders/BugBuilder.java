package databuilder.builders;

import databuilder.models.BugCommentModel;
import databuilder.models.BugModel;
import databuilder.models.BugUpdateModel;

/**
 * Created by richard on 08/08/2016.
 */
public class BugBuilder
{
    private BugModel bugModel;

    public BugModel BuildBasicNewBug()
    {
        return new BugModel.BugPayloadBuilder()
                .setProduct("TestProduct")
                .setComponent("TestComponent")
                .setSummary("testing")
                .setVersion("unspecified")
                .setOp_sys("Windows")
                .setRep_platform("PC")
                .setDescription("This is the description").build();
    }

    public BugUpdateModel BuildUpdateBug(int[] ids, String summary)
    {
        return new BugUpdateModel(summary, ids);
    }

    public BugCommentModel BuildBugComment(String commentDescription) { return new BugCommentModel(commentDescription, false, false); }

}
