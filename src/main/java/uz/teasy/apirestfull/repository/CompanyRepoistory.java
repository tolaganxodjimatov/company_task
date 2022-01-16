package uz.teasy.apirestfull.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.teasy.apirestfull.entity.Company;

@Repository
public interface CompanyRepoistory extends JpaRepository<Company, Integer> {
    boolean existsByCorpName(String corpName);

}
