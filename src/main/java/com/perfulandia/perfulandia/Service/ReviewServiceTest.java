package com.perfulandia.perfulandia.Service;
@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock private ReviewRepository reviewRepo;
    @InjectMocks private ReviewService reviewService;

    @Test
    void testFindAll() {
        Review review = new Review();
        review.setId(1);
        review.setComentario("Buen producto");
        when(reviewRepo.findAll()).thenReturn(List.of(review));

        List<Review> reviews = reviewService.findAll();

        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals("Buen producto", reviews.get(0).getComentario());
    }
}
