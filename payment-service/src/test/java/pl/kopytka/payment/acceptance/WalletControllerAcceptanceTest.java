/*
package pl.kopytka.payment.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import pl.kopytka.common.AcceptanceTest;
import pl.kopytka.common.BaseIntegrationTest;
import pl.kopytka.payment.web.dto.AddFundsRequest;
import pl.kopytka.payment.web.dto.CreateWalletRequest;
import pl.kopytka.payment.web.dto.WalletDto;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class WalletControllerAcceptanceTest extends BaseIntegrationTest {


    @Test
    @DisplayName("""
            given wallet exists,
            when funds are added,
            then wallet balance is updated and HTTP 204 No Content is returned""")
    void givenWalletExists_whenFundsAreAdded_thenWalletBalanceIsUpdatedAndHttp204Returned() {
        // given
        UUID customerId = UUID.randomUUID();
        BigDecimal initialAmount = new BigDecimal("100.00");
        BigDecimal additionalAmount = new BigDecimal("50.00");
        BigDecimal expectedFinalAmount = new BigDecimal("150.00");

        // Create wallet first
        CreateWalletRequest createRequest = new CreateWalletRequest(
                customerId,
                initialAmount
        );

        var createResponse = testRestTemplate.postForEntity(getBaseWalletsUrl(), createRequest, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        URI location = createResponse.getHeaders().getLocation();
        assertThat(location).isNotNull();

        String walletPath = location.getPath();
        String walletId = walletPath.substring(walletPath.lastIndexOf('/') + 1);

        // Add funds
        AddFundsRequest addFundsRequest = new AddFundsRequest(additionalAmount);

        // when
        var addFundsResponse = testRestTemplate.postForEntity(
                getBaseWalletsUrl() + "/" + walletId + "/funds",
                addFundsRequest,
                Void.class
        );

        // then
        assertThat(addFundsResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        // Verify the wallet balance was updated
        var getResponse = testRestTemplate.getForEntity(
                getBaseWalletsUrl() + "/" + walletId,
                WalletDto.class
        );

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        WalletDto updatedWallet = getResponse.getBody();
        assertThat(updatedWallet).isNotNull();
        assertThat(updatedWallet.balance()).isEqualByComparingTo(expectedFinalAmount);
    }

    @Test
    @DisplayName("""
            given wallet exists,
            when wallet is retrieved by customer ID,
            then wallet is returned and HTTP 200 OK is returned""")
    void givenWalletExists_whenWalletIsRetrievedByCustomerId_thenWalletIsReturnedAndHttp200Returned() {
        // given
        UUID customerId = UUID.randomUUID();
        BigDecimal initialAmount = new BigDecimal("100.00");

        // Create wallet first
        CreateWalletRequest createRequest = new CreateWalletRequest(
                customerId,
                initialAmount
        );

        var createResponse = testRestTemplate.postForEntity(getBaseWalletsUrl(), createRequest, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // when
        var getResponse = testRestTemplate.getForEntity(
                getBaseWalletsUrl() + "/customer/" + customerId,
                WalletDto.class
        );

        // then
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        WalletDto wallet = getResponse.getBody();
        assertThat(wallet).isNotNull();
        assertThat(wallet.customerId()).isEqualTo(customerId.toString());
        assertThat(wallet.balance()).isEqualByComparingTo(initialAmount);
    }

    @Test
    @DisplayName("""
            given non-existent wallet ID,
            when wallet is requested,
            then HTTP 404 Not Found is returned""")
    void givenNonExistentWalletId_whenWalletIsRequested_thenHttp404NotFoundReturned() {
        // given
        var nonExistentWalletId = UUID.randomUUID();

        // when
        var response = testRestTemplate.getForEntity(
                getBaseWalletsUrl() + "/" + nonExistentWalletId,
                Object.class
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody())
                .isNotNull()
                .extracting("message")
                .asString()
                .contains("Wallet with ID " + nonExistentWalletId + " not found");
    }

    String getBaseWalletsUrl() {
        return "http://localhost:" + port + "/api/wallets";
    }
}
*/
