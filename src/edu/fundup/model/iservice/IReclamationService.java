package edu.fundup.model.iservice;




import edu.fundup.model.entity.Reclamation;

import java.util.ArrayList;

public interface IReclamationService {
    void addReclamation(Reclamation reclamation);
    void updateReclamation(Reclamation reclamation);
    void deleteReclamation(Reclamation reclamation);
    ArrayList<Reclamation> getReclamations();
    Reclamation findReclamationById(int id);
    ArrayList<Reclamation> findReclamationByIdUser(int idUser);
    Reclamation findReclamationByTypeObjet(String typeObjet);
    Boolean reclamationExist(Reclamation reclamation);
    int reclamationCount(Reclamation reclamation);
}
