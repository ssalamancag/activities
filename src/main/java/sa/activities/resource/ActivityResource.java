package sa.activities.resource;

import sa.activities.model.Activity;
import sa.activities.model.Comment;
import sa.activities.service.ActivityService;


import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/activity")
public class ActivityResource {

    @Context
    UriInfo uriInfo;

    @EJB
    ActivityService activityService;

    @GET
    public List<Activity> getAllActivities(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return activityService.getAllActivities(first, maxResult);
    }

    @GET
    @Path("{id}")
    public Activity getActivityByID(@PathParam("id") int id){
        return activityService.getActivityById(id);
    }

    @GET
    @Path("/category/{category}")
    public List<Activity> getActivitiesByCategory(@PathParam("category") String category){
        return activityService.getActivitiesByCategory(category);
    }

    ///Create a activity
    @POST
    public Activity  createActivity( Activity activity){
        Activity y = activityService.createActivity(activity);
        return y;

        //return Response.status(Response.Status.CREATED).build();
    }


    //update activity
    @PUT
    @Path("{id}")
    public Activity updateActivity(@PathParam("id") int id, Activity activity) {
        return activityService.updateActivity(id, activity);
    }
    @PUT
    @Path("{id}/add/{user}")
    public Activity updateActivity(@PathParam("id") int id, @PathParam("user") String user ) {
        return activityService.addMember(id, user);
    }
    @PUT
    @Path("{id}/administrator/{admi}")
    public Response updateAdministrator(@PathParam("id") int id, Activity activity, @PathParam("admi") String administrator) {
        Activity response = activityService.updateAdministrator(administrator,id, activity);
        if(response != null) return Response.status(Response.Status.NO_CONTENT).build();
        else return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }


    @PUT
    @Path("{id}/comment")
    public Response  commentActivity(@PathParam("id") int id, Comment commet){
        activityService.addComment(id, commet);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteActivity(@PathParam("id") int id) {
        activityService.deleteActivity(id);
        return Response.status(Response.Status.OK).build();
    }


}
