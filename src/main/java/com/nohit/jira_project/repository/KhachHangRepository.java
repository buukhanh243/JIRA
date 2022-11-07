package com.nohit.jira_project.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.nohit.jira_project.model.*;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    public KhachHang findByEmail(String email);
}
