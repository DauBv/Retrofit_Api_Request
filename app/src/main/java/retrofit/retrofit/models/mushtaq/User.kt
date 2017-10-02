package retrofit.retrofit.models.mushtaq

import com.google.gson.annotations.SerializedName


/**
 * Created by DauBV on 29/09/2017.
 */
class User(name: String, job: String) {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("job")
    var job: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("createdAt")
    var createdAt: String? = null
}

