package com.socailmediaanalyzer.Response;

import lombok.Builder;

import java.beans.ConstructorProperties;

@Builder
public class Response<TData, TStatus> {
    private TData data;
    private boolean success;
    private TStatus status;

    @ConstructorProperties({"data", "success", "status"})
    public Response(TData data, boolean success, TStatus status) {
        this.data = data;
        this.success = success;
        this.status = status;
    }

    public Response() {
    }

    public static <TData, TStatus> Response<TData, TStatus> success(TData data, TStatus status) {
        return Response.<TData, TStatus>builder().data(data).success(true).status(status).build();
    }

    public static <TData, TStatus> Response<TData, TStatus> failure(TStatus status) {
        return Response.<TData, TStatus>builder().data(null).success(false).status(status).build();
    }

    public static <TData, TStatus> Response<TData, TStatus> failure(TData data, TStatus status) {
        return Response.<TData, TStatus>builder().data(data).success(false).status(status).build();
    }

    public static <TData, TStatus> Response.ResponseBuilder<TData, TStatus> builder() {
        return new Response.ResponseBuilder();
    }

    public TData getData() {
        return this.data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public TStatus getStatus() {
        return this.status;
    }

    public void setData(TData data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatus(TStatus status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof Response)) {
            return false;
        } else {
            Response other = (Response)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label39: {
                    Object thisData = this.getData();
                    Object otherData = other.getData();
                    if(thisData == null) {
                        if(otherData == null) {
                            break label39;
                        }
                    } else if(thisData.equals(otherData)) {
                        break label39;
                    }

                    return false;
                }

                if(this.isSuccess() != other.isSuccess()) {
                    return false;
                } else {
                    Object thisStatus = this.getStatus();
                    Object otherStatus = other.getStatus();
                    if(thisStatus == null) {
                        if(otherStatus != null) {
                            return false;
                        }
                    } else if(!thisStatus.equals(otherStatus)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Response;
    }

    public int hashCode() {
        byte result = 1;
        Object data = this.getData();
        int result1 = result * 59 + (data == null?43:data.hashCode());
        result1 = result1 * 59 + (this.isSuccess()?79:97);
        Object status = this.getStatus();
        result1 = result1 * 59 + (status == null?43:status.hashCode());
        return result1;
    }

    public String toString() {
        return "Response(data=" + this.getData() + ", success=" + this.isSuccess() + ", status=" + this.getStatus() + ")";
    }

    public static class ResponseBuilder<TData, TStatus> {
        private TData data;
        private boolean success;
        private TStatus status;

        ResponseBuilder() {
        }

        public Response.ResponseBuilder<TData, TStatus> data(TData data) {
            this.data = data;
            return this;
        }

        public Response.ResponseBuilder<TData, TStatus> success(boolean success) {
            this.success = success;
            return this;
        }

        public Response.ResponseBuilder<TData, TStatus> status(TStatus status) {
            this.status = status;
            return this;
        }

        public Response<TData, TStatus> build() {
            return new Response(this.data, this.success, this.status);
        }

        public String toString() {
            return "Response.ResponseBuilder(data=" + this.data + ", success=" + this.success + ", status=" + this.status + ")";
        }
    }
}
