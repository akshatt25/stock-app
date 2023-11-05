package com.example.infobyte;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/macros/echo?user_content_key=JJ2ufgnR1x9SBLUtl8YodFWM3EgATvnnucnoWpRQi1YsZUgWp7p9csByXkOClZ-6SNY-vL9-upmzqIcI6DwR7FQmEfWR5ON6m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnLLaPLSR1orrviIfdqbMVqnezuh_s26yzbzP1_QfvmA2sWZRCCTUDlAxB98yUCUmFTQrJMlj5zDWXRL6VL37MTTcpTNo-7Iq1kALmaJ23tE48xTm37iX3Fs&lib=MSKxoTD8KE-03f2YxPJKn5sQfnqTZGV-_")
    Call<List<StockItem>> getStockItems();
}
