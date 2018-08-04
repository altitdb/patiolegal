package br.com.patiolegal.dto;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.patiolegal.dto.SearchEntranceResponseDTO.SearchEntranceBuilder;

public class SearchEntranceResponseDTOTest {

	@Test
	public void shouldBuildWithDateTimeIn() {
		LocalDateTime dateTimeIn = LocalDateTime.now();

		SearchEntranceResponseDTO dto = new SearchEntranceBuilder().withDateTimeIn(dateTimeIn).build();

		Assert.assertEquals(dto.getDateTimeIn(), dateTimeIn);
	}

	@Test
	public void shouldBuildWithDateTimeOut() {
		LocalDateTime dateTimeOut = LocalDateTime.of(2018, 12, 23, 18, 55);

		SearchEntranceResponseDTO dto = new SearchEntranceBuilder().withDateTimeOut(dateTimeOut).build();

		Assert.assertEquals(dto.getDateTimeOut(), dateTimeOut);
	}

	@Test
	public void shouldBuildWithProtocol() {
		String protocol = "PROTOCOLO2015081520568978";

		SearchEntranceResponseDTO dto = new SearchEntranceBuilder().withProtocol(protocol).build();

		Assert.assertEquals(dto.getProtocol(), protocol);
	}

	@Test
	public void shouldBuildWithSportingPlate() {
		String sportingPlate = "AAE-9858";

		SearchEntranceResponseDTO dto = new SearchEntranceBuilder().withSportingPlate(sportingPlate).build();

		Assert.assertEquals(dto.getSportingPlate(), sportingPlate);
	}

	@Test
	public void shouldBuildWithOriginalPlate() {
		String originalPlate = "AAS-2356";

		SearchEntranceResponseDTO dto = new SearchEntranceBuilder().withOriginalPlate(originalPlate).build();

		Assert.assertEquals(dto.getOriginalPlate(), originalPlate);

	}
}
