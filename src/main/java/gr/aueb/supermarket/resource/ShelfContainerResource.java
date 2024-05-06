package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.domain.Shelf;
import gr.aueb.supermarket.domain.ShelfContainer;
import gr.aueb.supermarket.persistence.ShelfContainerRepository;
import gr.aueb.supermarket.persistence.ShelfRepository;
import gr.aueb.supermarket.representation.ShelfContainerMapper;
import gr.aueb.supermarket.representation.ShelfContainerRepresentation;
import gr.aueb.supermarket.representation.ShelfMapper;
import gr.aueb.supermarket.representation.ShelfRepresentation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;

import static gr.aueb.supermarket.resource.SuperMarketURI.SHELFCONTAINERS;

@Path(SHELFCONTAINERS)
@RequestScoped
public class ShelfContainerResource {
    @Inject
    ShelfContainerRepository shelfContainerRepository;

    @Inject
    ShelfContainerMapper shelfContainerMapper;

    @Inject
    ShelfRepository shelfRepository;

    @Inject
    ShelfMapper shelfMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response allShelfContainers(){
        List<ShelfContainer> shelfcontainers = shelfContainerRepository.listAll();

        if (shelfcontainers.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(shelfContainerMapper.toRepresentation(shelfcontainers)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{shelfcontainerId:[0-9]*}")
    @Transactional
    public Response findById(@PathParam("shelfcontainerId") int shelfcontainerId){
        ShelfContainer sc = shelfContainerRepository.findById(shelfcontainerId);
        if (sc == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return  Response.ok().entity(shelfContainerMapper.toRepresentation(sc)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response newShelfContainer(ShelfContainerRepresentation newSc){
        if (newSc == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ShelfContainer sc = shelfContainerMapper.toEntity(newSc);

        shelfContainerRepository.persist(sc);

        Long Id = sc.getId();

        URI uri = UriBuilder.fromResource(ProductResource.class).path(String.valueOf(Id)).build();

        return Response.created(uri).entity(shelfContainerMapper.toRepresentation(sc)).build();
    }

    @POST
    @Path("/{shelfcontainerId:[0-9]*}" + SHELFCONTAINERS)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response AddshelfToExistingShelfContainer(@PathParam("ShelfContainerID") int ShelfContainerID, ShelfRepresentation newShelf) {
        if (newShelf == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ShelfContainer ExistingSC = shelfContainerRepository.findById(ShelfContainerID);
        Shelf s = shelfMapper.toEntity(newShelf);

        ExistingSC.addShelf(s.GetShelfLevel(), s.GetCapacity());

        shelfContainerRepository.persistAndFlush(ExistingSC);
        shelfRepository.persist(s);

        int Id = s.getId();

        URI uri = UriBuilder.fromResource(ShelfResource.class).path(String.valueOf(Id)).build();
        return Response.created(uri).entity(shelfMapper.toRepresentation(s)).build();
    }
}
