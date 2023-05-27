package br.com.prepvest.utils.firebase

import br.com.prepvest.di.moshi
import br.com.prepvest.model.CourseModel
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import java.lang.reflect.Type

object RemoteConfigUtils {

    private const val FETCH_TIMEOUT = 60L

    private val instance get() = Firebase.remoteConfig(FirebaseApp.getInstance())

    val courseList get() = getList<CourseModel>("courseList")

    init {
        instance.setConfigSettingsAsync(
            remoteConfigSettings {
                fetchTimeoutInSeconds = FETCH_TIMEOUT
            }
        )
    }

    fun fetch(onFinished: (Boolean) -> Unit) = instance.fetchAndActivate()
        .addOnCompleteListener { onFinished(it.isSuccessful) }
        .addOnFailureListener { it.printStackTrace() }

    private fun getString(key: String) = instance.getString(key)

    private inline fun <reified T : Any> getList(key: String): List<T> = try {
        val json = getString(key)
        val type: Type = Types.newParameterizedType(MutableList::class.java, T::class.java)
        val adapter: JsonAdapter<MutableList<T>> = moshi.adapter(type)
        adapter.fromJson(json)!!
    } catch (e: Exception) {
        listOf()
    }
}