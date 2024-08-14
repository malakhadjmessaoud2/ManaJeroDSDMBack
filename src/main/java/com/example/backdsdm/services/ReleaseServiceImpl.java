package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.ReleaseRepository;
import com.example.backdsdm.entities.Release;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReleaseServiceImpl implements IReleaseService {

    private final ReleaseRepository releaseRepository;

    @Override
    public List<Release> getReleasesByDeploymentPlanId(String deploymentPlanId) {
        return releaseRepository.findByDeploymentPlanId(deploymentPlanId);
    }

    @Override
    public Release addRelease(String deploymentPlanId, String name, String details) {
        Release release = new Release();
        release.setDeploymentPlanId(deploymentPlanId);
        release.setName(name);
        release.setDetails(details);
        return releaseRepository.save(release);
    }

    @Override
    public Release updateRelease(String deploymentPlanId, String id, String name, String details) {
        // Recherche de la release par deploymentPlanId et releaseId
        Release release = releaseRepository.findByDeploymentPlanIdAndId(deploymentPlanId, id)
                .orElseThrow();

        // Mise à jour des champs de la release
        release.setName(name);
        release.setDetails(details);

        // Sauvegarde et retour de la release mise à jour
        return releaseRepository.save(release);
    }
    @Override
    public Release archiveRelease(String deploymentPlanId, String id) {
        Release release = releaseRepository.findByDeploymentPlanIdAndId(deploymentPlanId, id)
                .orElseThrow();

        release.setArchived(true);
        return releaseRepository.save(release);
    }
    @Override
    public void deleteRelease(String deploymentPlanId, String id) {
        Release release = releaseRepository.findByDeploymentPlanIdAndId(deploymentPlanId, id)
                .orElseThrow();

        releaseRepository.deleteById(id);
    }


}
