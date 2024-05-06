package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.domain.Product;
import gr.aueb.supermarket.domain.ProductPosition;
import gr.aueb.supermarket.domain.Shelf;
import gr.aueb.supermarket.persistence.ProductRepository;
import gr.aueb.supermarket.persistence.ShelfRepository;
import gr.aueb.supermarket.representation.ProductMapper;
import gr.aueb.supermarket.representation.ProductPositionRepresentation;
import gr.aueb.supermarket.representation.ProductRepresentation;
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

import static gr.aueb.supermarket.resource.SuperMarketURI.PRODUCTPOSITION;
import static gr.aueb.supermarket.resource.SuperMarketURI.PRODUCTS;

@Path(PRODUCTS)
@RequestScoped
public class ProductResource {

    @Inject
    ProductRepository productRepository;

    @Inject
    ShelfRepository shelfRepository;

    @Inject
    ProductMapper productMapper;

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response allProducts(){
        List<Product> products = productRepository.listAll();

        if(products.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(productMapper.toRepresentation(products)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{productId:[0-9]*}")
    @Transactional
    public Response findById(@PathParam("productId") int productId){
        Product product = productRepository.findById(productId);

        if (product == null) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(productMapper.toRepresentation(product)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response newProduct(ProductRepresentation productRepresentation){
        Product product = productMapper.toEntity(productRepresentation);

        if(product == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        productRepository.persist(product);

        int productId = product.getID();

        URI uri = UriBuilder.fromResource(ProductResource.class).path(String.valueOf(productId)).build();

        return Response.created(uri).entity(productMapper.toRepresentation(product)).build();
    }

    @POST
    @Path("{productId:[0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response changeProductPrice(@PathParam("productId") int productId, double price){
        Product product = productRepository.findById(productId);

        if(product == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        product.setPrice(price);
        productRepository.persistAndFlush(product);

        URI uri = UriBuilder.fromResource(ProductResource.class).path(String.valueOf(productId)).build();

        return Response.created(uri).entity(productMapper.toRepresentation(product)).build();
    }

    @POST
    @Path("/{productId:[0-9]*}" + PRODUCTPOSITION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addProductPosition(@PathParam("productId") int productId, ProductPositionRepresentation productPositionRepresentation){

        Product product = productRepository.findById(productId);
        Shelf shelf = shelfRepository.findById(productPositionRepresentation.shelfID);

        if(product == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(shelf == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ProductPosition pp = product.setProductPosition(productPositionRepresentation.quantity, shelf);

        em.persist(pp);
        shelfRepository.persistAndFlush(shelf);
        productRepository.persistAndFlush(product);

        return Response.ok().entity(productMapper.toRepresentation(product)).build();
    }

    @GET
    @Path("/{productId:[0-9]*}" + "/quantity")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getQuantityById(@PathParam("productId") int productId){
        Product product = productRepository.findById(productId);

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        int quantity = product.getProductPosition().GetQuantity();
        return Response.ok().entity(quantity).build();
    }
}
