/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 22/06/2022 14:36:18
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.repositories

import br.com.edersystems.backend.infrastructure.entities.product.ProdutoCosif
import br.com.edersystems.backend.infrastructure.entities.product.ProdutoCosifPK
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoCosifRepository : JpaRepository<ProdutoCosif, ProdutoCosifPK>
