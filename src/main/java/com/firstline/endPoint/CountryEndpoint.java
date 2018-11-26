package com.firstline.endPoint;

import com.firstline.dto.PatientDto;
import com.firstline.service.PatientService;
import com.firstline.soap.CountryRepository;
import com.firstline.soap.GetCountryRequest;
import com.firstline.soap.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://firstline.com/soap";

    private CountryRepository countryRepository;

    private final PatientService patientService;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository, PatientService patientService) {
        this.countryRepository = countryRepository;
        this.patientService = patientService;
    }



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        PatientDto patientDto = new PatientDto(request.getName());
        patientService.createPatient(patientDto);

        return response;
    }
}