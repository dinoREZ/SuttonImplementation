package de.fhws.fiw.fds.suttondemoMessage.api.service;


import de.fhws.fiw.fds.suttondemoMessage.api.states.*;
import de.fhws.fiw.fds.suttondemoMessage.model.Message;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageService extends AbstractService {

  /*
   These attributes From AbstractService Class are needed in all states:
    .setUriInfo(this.uriInfo)
	.setRequest(this.request)
	.setHttpServletRequest(this.httpServletRequest)
	.setContext(this.context)
   */


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelloMessage(){
        return new GetAllMessages.Builder()
                .setQuery(new GetAllMessages.AllMessages())
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .build()
                .execute();

                /*DaoFactory.getInstance().getMessageDao()
         .readByPredicate(Message -> true ).getResult()
           .stream().collect(Collectors.toList());*/
    }

    //GetSingleMessage mit ID
    @GET
    @Path ("{id: \\d+ }")
    public Response getSingleMessage(@PathParam("id") long id ){
    return   new GetSingleMessage.Builder()
            .setRequestedId(id)
            .setUriInfo(this.uriInfo)
            .setRequest(this.request)
            .setHttpServletRequest(this.httpServletRequest)
            .setContext(this.context)
            .build()
            .execute();

            //DaoFactory.getInstance().getMessageDao().readById(id).getResult();
    }

    @PUT
    @Path("{id: \\d+}")
    public Response updateMessage (final Message message , @PathParam("id") long id ){

      if (Character.isUpperCase(message.getText().charAt(0)))
          return new PutMessage.Builder()
                  .setRequestedId(id)
                  .setModelToUpdate(message)
                  .setUriInfo(this.uriInfo)
                  .setRequest(this.request)
                  .setHttpServletRequest(this.httpServletRequest)
                  .setContext(this.context)
                  .build()
                  .execute();
      // DaoFactory.getInstance().getMessageDao().update(message);
      else throw new WebApplicationException(Response.status(400).build());

    }

    //Add new Message
    @POST
    public Response addMessage (final Message message){
      if (Character.isUpperCase(message.getText().charAt(0)))
          return new PostMessage.Builder()
                  .setModelToCreate(message)
                  .setUriInfo(this.uriInfo)
                  .setRequest(this.request)
                  .setHttpServletRequest(this.httpServletRequest)
                  .setContext(this.context)
                  .build().execute();
       //DaoFactory.getInstance().getMessageDao().create(message);
      else throw new WebApplicationException(Response.status(400).build());

    }

    // Delete Message with id
    @DELETE
    @Path("{id: \\d+}")
    public Response deleteMessage(@PathParam("id") long id ){
        return new DeleteMessage.Builder()
                .setRequestedId(id)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .build().execute();
       // DaoFactory.getInstance().getMessageDao().delete(id);
    }


}
