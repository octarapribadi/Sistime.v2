package service;

import repo.RegistrasiMahasiswaManager;
import entity.RegistrasiMahasiswa;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/registrasimahasiswas")
@Produces(MediaType.APPLICATION_JSON)
public class RegistrasiMahasiswaService {
    @Inject
    RegistrasiMahasiswaManager registrasiMahasiswaManager;

    @GET
    public Response getAllRegistrasiMahasiswa(){
        List<RegistrasiMahasiswa> registrasiMahasiswas = registrasiMahasiswaManager.findAllRegistrasiMahasiswa();
        if(registrasiMahasiswas.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("{}").build();
        else
            return Response.ok(registrasiMahasiswas, MediaType.APPLICATION_JSON).build();
    }
}
