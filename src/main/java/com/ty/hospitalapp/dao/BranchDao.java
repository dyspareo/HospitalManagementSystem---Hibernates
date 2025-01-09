package com.ty.hospitalapp.dao;

import java.util.List;

import com.ty.hospitalapp.dto.Branch;

public interface BranchDao {
	
    Branch saveBranch(Branch branch,int hid);
	
	boolean deleteBranchById(int bid);
	
	Branch updateBranchById(int bid,Branch branch);
	
	Branch getBranchById(int bid);
	
	List<Branch> getAllBranch();
	
}