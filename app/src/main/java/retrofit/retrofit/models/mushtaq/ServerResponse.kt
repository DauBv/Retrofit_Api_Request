package retrofit.retrofit.models.mushtaq

import com.google.gson.annotations.SerializedName

/**
 * Created by daubv on 25/09/2017.
 */


class ServerResponse {
    @SerializedName("success")
    var success: Boolean = false
    @SerializedName("message")
    var message: String? = null

    var Successs: Boolean
        set(value) {
            success = value
        }
        get() {
            return success
        }
    var Mesages: String?
        set(value) {
            message = value
        }
        get() {
            return message
        }
}
