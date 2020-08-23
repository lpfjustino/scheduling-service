package com.jus10.schedule.repositories;

import com.jus10.schedule.models.Scheduling;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface SchedulingRepository extends CrudRepository<Scheduling, UUID> {
}