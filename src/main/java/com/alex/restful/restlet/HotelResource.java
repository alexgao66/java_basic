package com.alex.restful.restlet;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Created by gaojun on 15/12/14.
 */
public class HotelResource extends ServerResource{

    @Get
    public String getHotel() {
        return "get hotel!";
    }

    @Post("json")
    public String addHotel(Representation entity) {
        return "";
    }

}
