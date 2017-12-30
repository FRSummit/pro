package com.frsummit.hr2.serviceimpl;

import com.frsummit.hr2.jpa_repository.LeaveRepository;
import com.frsummit.hr2.jpa_repository.UserRepository;
import com.frsummit.hr2.model.Leaves;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service//("leaveService")
@Transactional
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Value("${spring.queries.leaves-list}")
    private String leavesListQuery;

///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////Not Applied/////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Value("${spring.queries.leaves-list-role}")
    private String leavesListRoleQuery;

    @Value("${spring.queries.leaves-list-co-ordinator-role}")
    private String leavesListCoOrdinatorRoleQuery;

    @Value("${spring.queries.leaves-list-co-ordinator-role2}")
    private String leavesListCoOrdinatorRoleQuery2;

///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Value("${spring.query.leave-apply-to-list}")
    private String applyToGetFromDb;

    @Value("${spring.query.leave-status-list}")
    private String leaveStatusGetFromDb;

    @Value("${spring.query.leave-modify-to-list}")
    private String leaveModifyToGetFromDb;

    @Value("${spring.queries.my-leaves-list}")
    private String loadMyAllLeavesFromDb;

    @Value("${spring.queries.my-last-leaves-status}")
    private String loadMyLastLeaveStatusFromDb;

    AuthenticationManagerBuilder auth;

    @Override
    public void saveLeave(Leaves leaves) {
        leaveRepository.save(leaves);
    }

    @Override
    public List<Leaves> findAllLeaves() {
        return entityManager.createQuery(leavesListQuery, Leaves.class).getResultList();
    }

    @Override
    public List<Leaves> findMyAllLeaves() {
        return entityManager.createQuery(loadMyAllLeavesFromDb + myId() + "'", Leaves.class).getResultList();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// Not Applied ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// Not Applied ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<Leaves> findAllLeavesForDin() {
        //return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.leaveStatus = '" + "Granted" + "' OR l.modifyTo = '" + "DIN" + "' OR l.actiondBy = '" + "CHAIRMAN" + "'", Leaves.class).getResultList();

        return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.applyTo = '" + applyToRole() + "' or l.modifyTo = '" + applyToRole() + "'", Leaves.class).getResultList();
    }

    @Override
    public List<Leaves> findAllLeavesForChairman() {
        //return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.leaveStatus = '" + "Granted" + "' OR l.modifyTo = '" + "CHAIRMAN" + "' OR l.actiondBy = '" + "CO-ORDINATOR" + "'", Leaves.class).getResultList();

        return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.applyTo = '" + applyToRole() + "' or l.modifyTo = '" + applyToRole() + "'", Leaves.class).getResultList();
    }

    @Override
    public List<Leaves> findAllLeavesForCoordinate() {
        //return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.leaveStatus = '" + "Granted" + "' OR l.modifyTo = '" + "CO-ORDINATOR" + "' OR l.actiondBy = '" + "FACULTY" + "'", Leaves.class).getResultList();

        return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.applyTo = '" + applyToRole() + "' or l.modifyTo = '" + applyToRole() + "'", Leaves.class).getResultList();
    }

    @Override
    public List<Leaves> findAllLeavesForFaculty() {
        //return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.modifyTo = '" + "FACULTY" + "'", Leaves.class).getResultList();

        return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.applyTo = '" + applyToRole() + "' and l.leaveStatus = '" + "Pending" + "'", Leaves.class).getResultList();
    }

//    @Override
//    public List<Leaves> findAllLeavesForChairman(String thisRole) {
//        return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.leaveStatus= '" + "Granted" + "' AND l.applyTo = '" + thisRole + "'", Leaves.class).getResultList();
//    }

    @Override
    public Leaves findSelectedLeaves(String usId) {
        TypedQuery<Leaves> query = entityManager.createQuery("SELECT l.applyTo FROM Leaves AS l WHERE l.leaveUserId='" + usId + "'", Leaves.class);
        return getSingleResultOrNull(query);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Override
    @Modifying
    public void updateLeaveStatus(String leaveId, String selectStatus, String leaveActionBy, String modifyTo) {
        Query query = entityManager.createQuery("UPDATE Leaves l SET l.leaveStatus = '" + selectStatus + "', l.actiondBy = '" + leaveActionBy + "', l.modifyTo = '" + modifyTo + "' WHERE l.leaveId='" + leaveId +"'");
        query.executeUpdate();
        System.out.println(leaveId + " " + selectStatus + " " + leaveActionBy);
    }

    @Override
    public void rollCall() {
        System.out.println(rolesQuery);
    }

    @Override
    public List<Leaves> findAllLeavesForRole() {
        //return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.applyTo = '" + applyToRole() + "'", Leaves.class).getResultList();
        return entityManager.createQuery(applyToGetFromDb + applyToRole() + "'" + leaveStatusGetFromDb + leaveModifyToGetFromDb + applyToRole() + "'", Leaves.class).getResultList();
    }

//    @Override
//    public String userSeenRole(String roleEmail) {
//        Query query = entityManager.createQuery("SELECT r FROM Role WHERE")
//        query.executeUpdate();

//    }



    public String myId(){
        String myId;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findByEmail(auth.getName());
        myId = u.getId().toString();
        return myId;
    }

    public String applyToRole(){
        String myRole;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findByEmail(auth.getName());
        myRole = u.getMyRole().toString();
        return myRole;
    }

    private Leaves getSingleResultOrNull(TypedQuery<Leaves> query) {
        query.setMaxResults(1);
        List<Leaves> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
