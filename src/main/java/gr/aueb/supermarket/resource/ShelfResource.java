package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.domain.Shelf;
import gr.aueb.supermarket.persistence.ShelfRepository;
import gr.aueb.supermarket.representation.ShelfMapper;
import gr.aueb.supermarket.representation.ShelfRepresentation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;

import static gr.aueb.supermarket.resource.SuperMarketURI.SHELVES;

@Path(SHELVES)
@RequestScoped
public class ShelfResource {

    @Inject
    ShelfRepository shelfRepository;

    @Inject
    ShelfMapper shelfMapper;

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response allShelves(){
        List<Shelf> shelves = shelfRepository.listAll();

        if (shelves.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }



        return Response.ok().entity(shelfMapper.toRepresentation(shelves)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{shelfId:[0-9]*}")
    @Transactional
    public Response findById(@PathParam("shelfId") int shelfId){
        Shelf shelf = shelfRepository.findById(shelfId);
        if (shelf == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return  Response.ok().entity(shelfMapper.toRepresentation(shelf)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response newShelf(ShelfRepresentation newShelf){
        if (newShelf == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Shelf shelf = shelfMapper.toEntity(newShelf);

        shelfRepository.persist(shelf);

        int Id = shelf.getId();

        URI uri = UriBuilder.fromResource(ProductResource.class).path(String.valueOf(Id)).build();

        return Response.created(uri).entity(shelfMapper.toRepresentation(shelf)).build();
    }
}
