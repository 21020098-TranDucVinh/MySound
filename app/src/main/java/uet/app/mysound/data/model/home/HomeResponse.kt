package uet.app.mysound.data.model.home

import uet.app.mysound.data.model.explore.mood.Mood
import uet.app.mysound.data.model.home.chart.Chart
import uet.app.mysound.utils.Resource


data class HomeResponse(
    val homeItem: Resource<ArrayList<HomeItem>>,
    val exploreMood: Resource<Mood>,
    val exploreChart: Resource<Chart>
)