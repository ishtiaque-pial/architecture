package com.example.structure.data

import com.example.structure.data.Prefence.PreferencesHelper
import com.example.structure.data.local.RoomHelper
import com.example.structure.data.remote.ApiHelper
import javax.inject.Inject

class DataManager @Inject constructor(
    preferencesHelper: PreferencesHelper, roomHelper: RoomHelper, apiHelper: ApiHelper
)  {

    val mPref = preferencesHelper
    val roomHelper = roomHelper
    val apiHelper = apiHelper

}