package com.perfulandia.perfulandia.Service;


import com.perfulandia.perfulandia.Repository.BranchRepository;

@ExtendWith(MockitoExtension.class)
 class BranchServiceTest {
    @Mock private BranchRepository branchRepo;
    @InjectMocks private BranchService branchService;

    @Test
    void testFindAll() {
        when(branchRepo.findAll()).thenReturn(List.of(new Branch("Sucursal Centro", "Dirección 123")));

        List<Branch> branches = branchService.findAll();

        assertNotNull(branches);
        assertEquals(1, branches.size());
        assertEquals("Sucursal Centro", branches.get(0).getNombre());
    }
}


