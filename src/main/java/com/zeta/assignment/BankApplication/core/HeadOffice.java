package main.java.com.zeta.assignment.BankApplication.core;

import main.java.com.zeta.assignment.BankApplication.exception.BankApplicationException;
import main.java.com.zeta.assignment.BankApplication.exception.ErrorEnum;

import java.util.ArrayList;
import java.util.List;

public class HeadOffice {

    private List<Branch> branches;

    public HeadOffice() {
        this.branches = new ArrayList<>();
    }

    public void createBranch() {
        Branch newBranch = new Branch(String.valueOf(1 + branches.size()));
        this.addBranch(newBranch);
    }

    public Branch getBranchById(String branchId) throws BankApplicationException {
        Integer branchIndex = Integer.valueOf(branchId);
        if(branchIndex < 1 || branchIndex > branches.size()) {
            throw new BankApplicationException(ErrorEnum.BRANCH_DOES_NOT_EXIST);
        }
        return this.branches.get(branchIndex - 1);
    }

    public List<Branch> getAllBranches() {
        return this.branches;
    }

    private void addBranch(Branch branch) {
        this.branches.add(branch);
    }

    @Override
    public String toString() {
        return "(" + branches.toString() + ")";
    }
}
