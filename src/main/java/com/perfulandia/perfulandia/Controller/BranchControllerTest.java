package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Service.BranchService;

@WebMvcTest(BranchController.class)
class BranchControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private BranchService branchService;

    @Test
    void testGetAllBranches() throws Exception {
        when(branchService.findAll()).thenReturn(List.of(new Branch("Sucursal Centro", "Dirección 123")));

        mockMvc.perform(get("/branches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Sucursal Centro"));
    }
}



