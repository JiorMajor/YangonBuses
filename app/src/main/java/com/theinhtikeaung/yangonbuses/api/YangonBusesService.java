package com.theinhtikeaung.yangonbuses.api;


import com.theinhtikeaung.yangonbuses.models.buses.Bus;
import com.theinhtikeaung.yangonbuses.models.busstop.BusStop;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by johnmajor on 17/1/17.
 */

public interface YangonBusesService {

    @GET("busline/search/{keyword}/{limit}")
    Observable<Bus> getBusline(@Path("keyword") String keyword, @Path("limit") int limit);

    @GET("busline/search/{keyword}")
    Observable<Bus> getBusline(@Path("keyword") String keyword);

    @GET("busline/search/")
    Observable<Bus> getBusline();

//    @GET("busline/busline/by-id/{id}")
//    Observable<Bus>


    @GET("busstop-cluster/search")
    Observable<BusStop> getBusStops();



}
