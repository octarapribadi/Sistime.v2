package rest.service;

import ejb.RegistrasiMahasiswaManager;
import model.RegistrasiMahasiswa;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("registrasimahasiswas")
@Produces(MediaType.APPLICATION_JSON)
public class RegistrasiMahasiswaService {
    @EJB
    RegistrasiMahasiswaManager registrasiMahasiswaManager;

    @GET
    public Response getAllRegistrasiMahasiswa(){
        List<RegistrasiMahasiswa> registrasiMahasiswas = registrasiMahasiswaManager.findAllRegistrasiMahasiswa();
        return Response.ok(registrasiMahasiswas, MediaType.APPLICATION_JSON).build();
    }
}
