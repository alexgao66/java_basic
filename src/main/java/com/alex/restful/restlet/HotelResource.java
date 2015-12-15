package com.alex.restful.restlet;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Created by gaojun on 15/12/14.
 */
public class HotelResource extends ServerResource{

    @Get
    public String getHotel() {
        return "get hotel!";
    }

}
