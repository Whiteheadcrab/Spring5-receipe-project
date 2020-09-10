package whiteheadcrab.springframework.services;

import whiteheadcrab.springframework.commands.UnitofMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService
{
    Set<UnitofMeasureCommand> listAllUoms();
}
