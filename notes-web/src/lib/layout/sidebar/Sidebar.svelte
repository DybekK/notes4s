<script lang="ts">
    import {Sidebar, SidebarWrapper} from 'flowbite-svelte'
    import NoteGroup from '$lib/layout/sidebar/NoteGroup.svelte'
</script>

<Sidebar class="sticky top-0">
        <SidebarWrapper class="h-full">
            <NoteGroup/>
        </SidebarWrapper>
</Sidebar>
