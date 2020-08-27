//package com.trivago.hotel;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class HotelSearchApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//@Test
//void shouldContainTwoElements() {
//	assertThat(list).hasSize(EXPECTED_SIZE);
//}
//
//
//	class WhenWeCompareTwoLists {
//
//		private final List<Integer> FIRST = Arrays.asList(1, 2, 3);
//		private final List<Integer> SECOND = Arrays.asList(1, 2, 3);
//
//		@Test
//		@DisplayName("Should contain the same elements")
//		void shouldContainSameElements() {
//			assertThat(FIRST).isEqualTo(SECOND);
//		}
//
//		@Test
//		@DisplayName("Should contain the same elements (with custom error message)")
//		void shouldContainSameElementsWithCustomErrorMessage() {
//			assertThat(FIRST)
//					.overridingErrorMessage(
//							"Expected the list to contain: %s but it contained: %s",
//							Arrays.toString(SECOND.toArray()),
//							Arrays.toString(FIRST.toArray())
//					)
//					.isEqualTo(SECOND);
//		}
//	}
//
//	@BeforeEach
//	void createAndInitializeList() {
//		first = new Object();
//		second = new Object();
//
//		list = Arrays.asList(first, second);
//	}
//}
