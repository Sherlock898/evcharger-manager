package com.noder.chargerCentralApi.config;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.noder.chargerCentralApi.models.Privilege;
import com.noder.chargerCentralApi.models.Role;
import com.noder.chargerCentralApi.models.User;
import com.noder.chargerCentralApi.repositories.PrivilegeRepository;
import com.noder.chargerCentralApi.repositories.RoleRepostiory;
import com.noder.chargerCentralApi.repositories.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
public class DataLoader {
    private final UserRepository userRepository;
    private final RoleRepostiory roleRepostiory;
    private final PrivilegeRepository privilegeRepository;

    public DataLoader(UserRepository userRepository, RoleRepostiory roleRepostiory, PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.roleRepostiory = roleRepostiory;
        this.privilegeRepository = privilegeRepository;
    }

    @PostConstruct
    @Transactional
    public void loadData() {
        // User privileges
        Privilege readOwnTransactions = createPrivilegeIfNotFound("READ_OWN_TRANSACTIONS"); // Read the charging sessions info
        Privilege readOwnAccountInfo = createPrivilegeIfNotFound("READ_OWN_ACCOUNT_INFO");  // (name, email, etc.)
        Privilege updateOwnAccountInfo = createPrivilegeIfNotFound("UPDATE_OWN_ACCOUNT_INFO"); // Update the account info
        Privilege readChargerInfo = createPrivilegeIfNotFound("READ_CHARGER_INFO"); // Read charger information and disponibility
        Privilege reserveCharger = createPrivilegeIfNotFound("RESERVE_CHARGER"); // Reserve a charger
        
        // Sub admin privileges
        Privilege readOwnTransactionsAdmin = createPrivilegeIfNotFound("READ_OWN_TRANSACTIONS_ADMIN"); // Read the charging sessions info of all chargers assosiated with him
        Privilege manageCharger = createPrivilegeIfNotFound("MANAGE_CHARGER"); // Control the charger (turn on/off)

        // Admin privileges
        Privilege changeElectricityPrice = createPrivilegeIfNotFound("CHANGE_ELECTRICITY_PRICE"); // Change the price of electricity
        Privilege createSubordinatedSubAdmin = createPrivilegeIfNotFound("CREATE_SUBORDINATED_SUB_ADMIN"); // Create a subordinated sub admin
        Privilege deleteSubordinatedSubAdmin = createPrivilegeIfNotFound("DELETE_SUBORDINATED_SUB_ADMIN"); // Delete a subordinated sub admin
        Privilege updateSubordinatedSubAdmin = createPrivilegeIfNotFound("UPDATE_SUBORDINATED_SUB_ADMIN"); // Update a subordinated sub admin
        Privilege registerOwnCharger = createPrivilegeIfNotFound("REGISTER_OWN_CHARGER"); // Register a charger
        Privilege deleteOwnCharger = createPrivilegeIfNotFound("DELETE_OWN_CHARGER"); // Delete a charger
        
        // System admin privileges
        Privilege readAllTransactions = createPrivilegeIfNotFound("READ_ALL_TRANSACTIONS"); // Read all the charging sessions info
        Privilege readAllChargers = createPrivilegeIfNotFound("READ_ALL_CHARGERS"); // Read all the charger information
        Privilege createAllAccounts = createPrivilegeIfNotFound("CREATE_ALL_ACCOUNTS"); // Create an account
        Privilege deleteAllAccounts = createPrivilegeIfNotFound("DELETE_ALL_ACCOUNTS"); // Delete an account
        Privilege updateAllAccounts = createPrivilegeIfNotFound("UPDATE_ALL_ACCOUNTS"); // Update an account
        Privilege readAllAccounts = createPrivilegeIfNotFound("READ_ALL_ACCOUNTS"); // Read all the account information

        List<Privilege> userPrivileges = List.of(readOwnTransactions, readOwnAccountInfo, updateOwnAccountInfo, readChargerInfo, reserveCharger);
        List<Privilege> subAdminPrivileges = List.of(readOwnTransactionsAdmin, manageCharger);
        List<Privilege> adminPrivileges = List.of(changeElectricityPrice, createSubordinatedSubAdmin, deleteSubordinatedSubAdmin, updateSubordinatedSubAdmin, registerOwnCharger, deleteOwnCharger);
        for (Privilege privilege : subAdminPrivileges) adminPrivileges.add(privilege);
        List<Privilege> systemAdminPrivileges = List.of(readAllTransactions, readAllChargers, createAllAccounts, deleteAllAccounts, updateAllAccounts, readAllAccounts);
    
        createRoleIfNotFound("ROLE_USER", userPrivileges);
        createRoleIfNotFound("ROLE_SUB_ADMIN", subAdminPrivileges);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);

        Role roleSysAdmin = createRoleIfNotFound("ROLE_SYS_ADMIN", systemAdminPrivileges);
    
        // Create test system admin
        User sysAdminUser = new User();
        sysAdminUser.setFirstName("System");
        sysAdminUser.setLastName("Admin");
        sysAdminUser.setEmail("admin@admin");
        sysAdminUser.setPin("1111");
        sysAdminUser.setRoles(List.of(roleSysAdmin));
        userRepository.save(sysAdminUser);

    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, List<Privilege> privileges) {
        Role role = roleRepostiory.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepostiory.save(role);
        }
        return role;
    }
}


