package com.kdb.clients.web;


import com.kdb.clients.service.ClientService;
import com.kdb.clients.service.IClientService;
import com.kdb.clients.service.dto.ClientDTO;
import com.kdb.clients.service.dto.TotalExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    public IClientService service = new ClientService();

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable long id) {
        ClientDTO client = service.GetClient(id);
        if (client == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ResponseEntity<ClientDTO> responseEntity = new ResponseEntity<>(client, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/clients/{id}/expenses/total")
    public ResponseEntity<TotalExpenseDTO> getExpenses(@PathVariable long id) {
        TotalExpenseDTO total = service.GetTotalExpenses(id);
        if (total == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ResponseEntity<TotalExpenseDTO> responseEntity = new ResponseEntity<>(total, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/clients")
    public ResponseEntity<Long> postClient( @RequestBody String name) {
        Long clientId = service.AddClient(name);
        ResponseEntity<Long> responseEntity = new ResponseEntity<Long>(clientId, HttpStatus.CREATED);
        return responseEntity;
    }
}