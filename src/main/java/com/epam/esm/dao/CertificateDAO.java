package com.epam.esm.dao;

import com.epam.esm.dto.request.CertificateFindByDTO;
import com.epam.esm.model.GiftCertificate;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CertificateDAO extends EntityDAO<GiftCertificate> {

    /**
     * @param certificate a list of changed fields
     * @return result this operation
     */
    GiftCertificate update(GiftCertificate certificate);

    /**
     * get all entity by parameters
     *
     * @return list of certificates.
     */
    List<GiftCertificate> listItems(CertificateFindByDTO certificateFindByDTO);


    /**
     * get all entity by description
     *
     * @return list of certificates.
     */


    List<GiftCertificate> findByTagName(String tagName);

    /**
     * @param id just id founded entity
     * @return result from DB
     */

    Optional<GiftCertificate> getCertificateById(@RequestParam int id);


    /**
     * @param certificateDTO createdOperation
     * @return result from DB
     */
    GiftCertificate createCertificate(GiftCertificate certificateDTO);

}
