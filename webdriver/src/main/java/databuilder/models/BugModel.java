package databuilder.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BugModel
{

    @JsonProperty
    private String product;
    @JsonProperty
    private String description;
    @JsonProperty
    private String component;
    @JsonProperty
    private String summary;
    @JsonProperty
    private String version;
    @JsonProperty
    private String op_sys;
    @JsonProperty
    private String rep_platform;

    public BugModel(String product, String component, String summary, String version, String op_sys, String rep_platform, String description){
        this.product = product;
        this.component = component;
        this.summary = summary;
        this.version = version;
        this.op_sys = op_sys;
        this.rep_platform = rep_platform;
        this.description = description;
    }

    public String getProduct() {
        return product;
    }

    public String getComponent() {
        return component;
    }

    public String getSummary() {
        return summary;
    }

    public String getVersion() {
        return version;
    }

    public String getOp_sys() {
        return op_sys;
    }

    public String getRep_platform() {
        return rep_platform;
    }

    public String getDescription() {
        return description;
    }

    public static class BugPayloadBuilder {
        private String product;
        private String component;
        private String summary;
        private String version;
        private String op_sys;
        private String rep_platform;
        private String description;

        public BugPayloadBuilder setProduct(String product) {
            this.product = product;
            return this;
        }

        public BugPayloadBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public BugPayloadBuilder setComponent(String component) {
            this.component = component;
            return this;
        }

        public BugPayloadBuilder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public BugPayloadBuilder setVersion(String version) {
            this.version = version;
            return this;
        }

        public BugPayloadBuilder setOp_sys(String op_sys) {
            this.op_sys = op_sys;
            return this;
        }

        public BugPayloadBuilder setRep_platform(String rep_platform) {
            this.rep_platform = rep_platform;
            return this;
        }

        public BugModel build(){
            return new BugModel(product, component, summary, version, op_sys, rep_platform, description);
        }
    }
}
