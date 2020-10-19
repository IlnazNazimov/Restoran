package ru.innopolis.stc27.maslakov.enterprise.project42.services.table;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.stc27.maslakov.enterprise.project42.dto.TableDTO;
import ru.innopolis.stc27.maslakov.enterprise.project42.repository.api.TableRepository;
import ru.innopolis.stc27.maslakov.enterprise.project42.utils.TableDTOConverter;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    @Override
    public TableDTO getTable(Long tableId) {
        return TableDTOConverter.convert(
                tableRepository.findById(tableId)
                        .orElseThrow(() -> new IllegalStateException("Стола с id #" + tableId + " не существует"))
        );
    }
}
